from marshmallow import Schema, fields
from sqlalchemy import Column, Integer, Integer, String, Boolean
from .model import Model, Base
import uuid
from sqlalchemy.dialects.postgresql import UUID


class Proyecto(Model, Base):
    __tablename__ = 'proyectos'
    nombre = Column(String)
    description = Column(String)
    objetivo = Column(String)

    def __init__(
        self, nombre, description,
        objetivo
    ):
        Model.__init__(self)
        self.nombre = nombre
        self.description = description
        self.objetivo = objetivo


class ProyectoSchema(Schema):
    nombre = fields.Str()
    description = fields.Str()
    objetivo = fields.Str()
