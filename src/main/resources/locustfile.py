import time

from locust import HttpUser, task, between


class QuickstartUser(HttpUser):

    wait_time = between(1, 2)

    @task

    def tiny_url(self):

        r1 = self.client.get("/tinyurl/{shortUrlid}", allow_redirects=False)

        r2 = self.client.get("/tinyurl/{shortUrlid}/{customurlid}", allow_redirects=False)

    def on_start(self):

        self.client.post("/tinyurl/create", json={"sourceUrl": {Any source URL})
