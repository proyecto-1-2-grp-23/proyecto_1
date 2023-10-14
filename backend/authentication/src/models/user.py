from sqlalchemy import Column, String
from .model import Model, Base


class User(Model, Base):
    __tablename__ = 'users'

    username = Column(String)
    email = Column(String)
    fullName = Column(String)

    def __init__(self, username, email, fullName):
        Model.__init__(self)
        self.username = username
        self.email = email
        self.fullName = fullName

