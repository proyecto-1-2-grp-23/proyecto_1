from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Boolean
from .model import Model, Base
import bcrypt
from datetime import datetime, timedelta
from uuid import uuid4


class User(Model, Base):
    __tablename__ = 'users'

    correo = Column(String)#
    password = Column(String)
    salt = Column(String)
    tipoIdentificacion = Column(String)
    token = Column(String)
    pais = Column(String)#
    ciudad = Column(String)#
    activo = Column(Boolean)
    expireAt = Column(DateTime)

    def __init__(self, correo, password, pais, ciudad):
        Model.__init__(self)
        self.activo = True
        self.correo = correo
        self.pais = pais
        self.ciudad = ciudad

        password = password.encode('utf-8')
        salt = bcrypt.gensalt()

        self.password = bcrypt.hashpw(password, salt).decode()
        self.salt = salt.decode()
        self.set_token()

    def set_token(self):
        self.token = str(uuid4())
        self.expireAt = datetime.now() + timedelta(hours=1)


class UserSchema(Schema):
    id = fields.Int()
    activo = fields.Boolean()
    correo = fields.Str()
    pais = fields.Str()
    ciudad = fields.Str()
    password = fields.Str()
    salt = fields.Str()
    token = fields.Str()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedUserJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class GeneratedTokenUserJsonSchema(Schema):
    id = fields.Int()
    token = fields.Str()
    expireAt = fields.DateTime()


class UserJsonSchema(Schema):
    id = fields.Int()
    correo = fields.Str()
    pais = fields.Str()
    ciudad = fields.Str()
    