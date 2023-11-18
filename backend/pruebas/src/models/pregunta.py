import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey, Date
from .model import Model, Base
from datetime import datetime, timedelta


class Pregunta(Model, Base):
    __tablename__ = "preguntas"

    id = Column(Integer, primary_key=True)
    idProyecto = Column(Integer)
    dificultad = Column(Integer)
    descripcion = Column(String)

    def __init__(self,idEmpresa,nombre,descripcion,perfiles,conocimientos_tecnicos,
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