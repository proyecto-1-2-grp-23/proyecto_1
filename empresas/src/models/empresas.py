from marshmallow import Schema, fields
from sqlalchemy import Column, Integer, Integer, String, Boolean
from .model import Model, Base
import uuid
from sqlalchemy.dialects.postgresql import UUID


class Empresa(Model, Base):
    __tablename__ = 'empresas'
    nombre = Column(String)
    nit = Column(String)
    correo = Column(String)

    def __init__(
        self, nombre, nit,
        correo
    ):
        Model.__init__(self)
        self.nombre = nombre
        self.nit = nit
        self.correo = correo


class EmpresaSchema(Schema):
    nombre = fields.Str()
    nit = fields.Str()
    correo = fields.Str()
