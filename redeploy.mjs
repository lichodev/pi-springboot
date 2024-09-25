const API = "https://priminf.lichodev.com.ar/portainer/api";
const id = 1;
const endpointId = 2;
const headers = {
  "X-API-Key": process.env.PORTAINER_ACCESS_TOKEN,
};

let body = {
  id,
  prune: true,
  pullImage: true,
};

let { StackFileContent } = await (
  await fetch(`${API}/stacks/${id}/file`, { headers })
).json();

let { Env } = await (await fetch(`${API}/stacks/${id}`, { headers })).json();

body = { ...body, stackFileContent: StackFileContent, env: Env };

const { ok, status } = await fetch(`${API}/stacks/${id}?endpointId=${endpointId}`, {
  headers,
  body: JSON.stringify(body),
  method: "PUT",
});

console.log({ ok, status });
