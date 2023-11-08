from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Boolean
from .model import Model, Base
from sqlalchemy import Column, String, DateTime, Boolean, Integer, ForeignKey


class Equipo(Model, Base):
    __tablename__ = "equipo"
    id = Column(Integer, primary_key=True)
    nombre = Column(String)
    descripcion = Column(String)
    idEmpresa = Column(Integer, ForeignKey("empresas.id"))

    def __init__(self, nombre, descripcion, idEmpresa):
        Model.__init__(self)
        self.nombre = nombre
        self.descripcion = descripcion
        self.idEmpresa = idEmpresa


class EquipoSchema(Schema):
    id = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedEquipoJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class EquipoJsonSchema(Schema):
    id = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    idEmpresa = fields.Int()
    createdAt = fields.DateTime()
