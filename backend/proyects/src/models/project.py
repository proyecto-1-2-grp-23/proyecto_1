import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey, Date
from .model import Model, Base
from datetime import datetime, timedelta


class Project(Model, Base):
    __tablename__ = "projects"

    id = Column(Integer, primary_key=True)
    idEmpresa = Column(Integer)
    nombre = Column(String)
    descripcion = Column(String)
    perfiles = Column(String)
    conocimientos_tecnicos = Column(String)
    habilidades_blandas = Column(String)
    startDate = Column(Date)
    finishDate = Column(Date)

    def __init__(
        self,
        idEmpresa,
        nombre,
        descripcion,
        perfiles,
        conocimientos_tecnicos,
        habilidades_blandas,
        startDate,
        finishDate,
    ):
        Model.__init__(self)
        self.idEmpresa = idEmpresa
        self.nombre = nombre
        self.descripcion = descripcion
        self.perfiles = perfiles
        self.conocimientos_tecnicos = conocimientos_tecnicos
        self.habilidades_blandas = habilidades_blandas
        self.startDate = startDate
        self.finishDate = finishDate


class ProjectSchema(Schema):
    id = fields.Int()
    idEmpresa = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    perfiles = fields.Str()
    conocimientos_tecnicos = fields.Str()
    habilidades_blandas = fields.Str()
    startDate = fields.DateTime()
    finishDate = fields.DateTime()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedProjectJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class ProyectoJsonSchema(Schema):
    id = fields.Int()
    idEmpresa = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    perfiles = fields.Str()
    conocimientos_tecnicos = fields.Str()
    habilidades_blandas = fields.Str()
    startDate = fields.DateTime()
    finishDate = fields.DateTime()
    createdAt = fields.DateTime()
