const API = "https://priminf.unicen.edu.ar/portainer/api";
const headers = {
  "X-API-Key": process.env.PORTAINER_ACCESS_TOKEN,
};

let body = {
  id: 1,
  prune: true,
  pullImage: true,
};

let { StackFileContent } = await (
  await fetch(`${API}/stacks/1/file`, { headers })
).json();

let { Env } = await (await fetch(`${API}/stacks/1`, { headers })).json();

body = { ...body, stackFileContent: StackFileContent, env: Env };

const { ok, status } = await fetch(`${API}/stacks/1?endpointId=2`, {
  headers,
  body: JSON.stringify(body),
  method: "PUT",
});

console.log({ ok, status });
