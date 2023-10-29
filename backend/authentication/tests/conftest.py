import os
from dotenv import load_dotenv, find_dotenv

load_dotenv()

os.environ["ENV"] = "test"


def pytest_configure(config):
    env_file = find_dotenv("../.env.test")
    load_dotenv(env_file)
    return config
