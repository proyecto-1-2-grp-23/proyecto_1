import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Integer, ForeignKey
from .model import Model, Base
from sqlalchemy.orm import relationship


class DataLaboral(Model, Base):
    __tablename__ = "datos_laborales_candidato"

    id = Column(Integer, primary_key=True)
    nombre_empresa = Column(String)
    rol = Column(String)
    funciones = Column(String)
    fecha_inicio = Column(DateTime)
    fecha_fin = Column(DateTime)
    habilidades = Column(String)
    idUsuario = Column(Integer, ForeignKey("users.id"))

    def __init__(
        self,
        nombre_empresa,
        rol,
        funciones,
        fecha_inicio,
        fecha_fin,
        habilidades,
        idUsuario,
    ):
        Model.__init__(self)
        self.nombre_empresa = nombre_empresa
        self.rol = rol
        self.funciones = funciones
        self.fecha_inicio = fecha_inicio
        self.fecha_fin = fecha_fin
        self.habilidades = habilidades
        self.idUsuario = idUsuario


class DataLaboralSchema(Schema):
    id = fields.Int()
    nombre_empresa = fields.Str()
    rol = fields.Str()
    funciones = fields.Str()
    fecha_inicio = fields.DateTime()
    fecha_fin = fields.DateTime()
    habilidades = fields.Str()
    idUsuario = fields.Int()
    createdAt = fields.DateTime()


class CreatedDataLaboralJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class DataLaboralJsonSchema(Schema):
    id = fields.Int()
    nombre_empresa = fields.Str()
    rol = fields.Str()
    funciones = fields.Str()
    fecha_inicio = fields.DateTime()
    fecha_fin = fields.DateTime()
    habilidades = fields.Str()
    idUsuario = fields.Int()
    createdAt = fields.DateTime()
