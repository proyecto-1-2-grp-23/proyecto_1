from sqlalchemy.orm import sessionmaker
from sqlalchemy import create_engine
import os


class SessionConfig():
    def __init__(self):
        ...

    def url(self):
        if os.environ.get('ENV'):
            db_user = os.environ.get('DB_USER') or 'postgres'
            db_pass = os.environ.get('DB_PASSWORD') or 'postgres'
            db_host = os.environ.get('DB_HOST') or 'localhost'
            db_port = os.environ.get('DB_PORT') or '5432'
            db_name = os.environ.get('DB_NAME') or 'monitor_users'
            return f'postgresql://{db_user}:{db_pass}@{db_host}:{db_port}/{db_name}'
        else:
            return 'sqlite:///test.db'

session_config = SessionConfig()
engine = create_engine(session_config.url())
Session = sessionmaker(bind=engine)
