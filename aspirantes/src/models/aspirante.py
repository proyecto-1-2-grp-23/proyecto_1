from marshmallow import Schema, fields
from sqlalchemy import Column, Integer, Integer, String, Boolean
from .model import Model, Base
import uuid
from sqlalchemy.dialects.postgresql import UUID


class Aspirante(Model, Base):
    __tablename__ = 'aspirantes'
    nombres = Column(String)
    apellidos = Column(String)
    correo = Column(String)

    def __init__(
        self, nombres, apellidos,
        correo
    ):
        Model.__init__(self)
        self.nombres = nombres
        self.apellidos = apellidos
        self.correo = correo


class AspiranteSchema(Schema):
    nombres = fields.Str()
    apellidos = fields.Str()
    correo = fields.Str()
