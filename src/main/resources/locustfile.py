import time

from locust import HttpUser, task, between


class QuickstartUser(HttpUser):

    wait_time = between(1, 2)

    @task

    def tiny_url(self):

        r1 = self.client.get("/tinyurl/09f0bcc", allow_redirects=False)

        r2 = self.client.get("/tinyurl/ac6bb66", allow_redirects=False)

    def on_start(self):

        self.client.post("/tinyurl/create", json={"sourceUrl": "https://www.tutorialspoint.com/spring_boot/spring_boot_introduction.htm"})
