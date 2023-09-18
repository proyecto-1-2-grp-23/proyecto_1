from marshmallow import Schema, fields
from sqlalchemy import Column, Integer, Integer, String, Boolean
from .model import Model, Base
import uuid
from sqlalchemy.dialects.postgresql import UUID


class Prueba(Model, Base):
    __tablename__ = 'pruebas'
    titulo = Column(String)
    description = Column(String)
    supervisor = Column(String)

    def __init__(
        self, titulo, description,
        supervisor
    ):
        Model.__init__(self)
        self.titulo = titulo
        self.description = description
        self.supervisor = supervisor


class PruebaSchema(Schema):
    titulo = fields.Str()
    description = fields.Str()
    supervisor = fields.Str()
