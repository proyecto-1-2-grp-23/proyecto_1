from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Boolean
from .model import Model, Base
from sqlalchemy import Column, String, DateTime, Boolean, Integer, ForeignKey


class Equipo(Model, Base):
    __tablename__ = "equipo"
    id = Column(Integer, primary_key=True)
    idEmpresa = Column(Integer, ForeignKey("empresas.id"))
    nombre = Column(String)
    descripcion = Column(String)

    def __init__(self, idEmpresa, nombre, descripcion):
        Model.__init__(self)
        self.idEmpresa = idEmpresa
        self.nombre = nombre
        self.descripcion = descripcion


class EquipoSchema(Schema):
    id = fields.Int()
    idEmpresa = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedEquipoJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class EquipoJsonSchema(Schema):
    id = fields.Int()
    idEmpresa = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    createdAt = fields.DateTime()
