import http from "k6/http";
import { check, group } from "k6";

const isNumeric = (value) => /^\d+$/.test(value);

const default_vus = 50;

const target_vus_env = `${__ENV.TARGET_VUS}`;
const target_vus = isNumeric(target_vus_env)
  ? Number(target_vus_env)
  : default_vus;

export let options = {
  stages: [
    // Ramp-up from 1 to TARGET_VUS virtual users (VUs) in 5s
    { duration: "5s", target: target_vus },

    // Stay at rest on TARGET_VUS VUs for 10s
    { duration: "30s", target: target_vus },

    // Ramp-down from TARGET_VUS to 0 VUs for 5s
    { duration: "5s", target: 0 },
  ],
};

// Create a random string of given length
function randomString(length, charset = "") {
  if (!charset) charset = "abcdefghijklmnopqrstuvwxyz";
  let res = "";
  while (length--) res += charset[(Math.random() * charset.length) | 0];
  return res;
}

const FIRSTNAME = `${randomString(10)}-first`;
const LASTNAME = `${randomString(10)}-last`;
const FULLNAME = `${FIRSTNAME} ${LASTNAME}`;
const USERNAME = `${FIRSTNAME}-${LASTNAME}`;

const BASE_URL = "http://host.docker.internal:8081";

export default () => {
  const requestConfigWithTag = (tag) => ({
    headers: {
      "Content-Type": "application/json",
    },
    tags: Object.assign(
      {},
      {
        name: "Users",
      },
      tag
    ),
  });

  let URL = `${BASE_URL}/api/v1/users`;

  group("01. Create a new user", () => {
    const payload = JSON.stringify({
      first_name: FIRSTNAME,
      last_name: LASTNAME,
      full_name: FULLNAME,
      user_name: USERNAME,
    });

    const res = http.post(
      URL,
      payload,
      requestConfigWithTag({ name: "Create" })
    );

    if (check(res, { "User created correctly": (r) => r.status === 200 })) {
      URL = `${URL}/${res.json("id")}`;
    } else {
      console.log(`Unable to create a User ${res.status} ${res.body}`);
      return;
    }
  });

  group("02. Fetch Users", () => {
    const res = http.get(
      `${BASE_URL}/api/v1/users`,
      requestConfigWithTag({ name: "Fetch" })
    );
    check(res, { "retrieved users status": (r) => r.status === 200 });
    check(res.json(), { "retrieved users list": (r) => r.length > 0 });
  });

  group("03. Update the user", () => {
    const payload = JSON.stringify({
      first_name: `${FIRSTNAME}-updated`,
      last_name: `${LASTNAME}-updated`,
      full_name: `${FIRSTNAME} ${LASTNAME}`,
      user_name: USERNAME,
    });
    const res = http.put(
      URL,
      payload,
      requestConfigWithTag({ name: "Update" })
    );
    const isSuccessfulUpdate = check(res, {
      "Update worked": () => res.status === 200,
    });

    if (!isSuccessfulUpdate) {
      console.log(`Unable to update the user ${res.status} ${res.body}`);
      return;
    }
  });

  group("04. Delete the user", () => {
    const delRes = http.del(
      URL,
      null,
      requestConfigWithTag({ name: "Delete" })
    );

    const isSuccessfulDelete = check(null, {
      "User was deleted correctly": () => delRes.status === 200,
    });

    if (!isSuccessfulDelete) {
      console.log(`User was not deleted properly`);
      return;
    }
  });
};
