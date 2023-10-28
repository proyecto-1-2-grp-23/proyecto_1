from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Boolean
from .model import Model, Base
from sqlalchemy import Column, String, DateTime, Boolean, Integer, ForeignKey


class Equipo(Model, Base):
    __tablename__ = "equipo"
    id = Column(Integer, primary_key=True)
    nombre = Column(String)
    descripcion = Column(String)
    empleados = Column(String)
    idEmpresa = Column(Integer, ForeignKey("empresas.id"))

    def __init__(self, nombre, descripcion, empleados, idEmpresa):
        Model.__init__(self)
        self.nombre = nombre
        self.descripcion = descripcion
        self.empleados = empleados
        self.idEmpresa = idEmpresa


class EquipoSchema(Schema):
    id = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    empleados = fields.Str()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedEmpresaJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class EmpresaJsonSchema(Schema):
    id = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    empleados = fields.Str()
    idEmpresa = fields.Int()