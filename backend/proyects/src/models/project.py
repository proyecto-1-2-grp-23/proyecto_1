import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer
from .model import Model, Base
from datetime import datetime, timedelta

class Project(Model, Base):
    __tablename__ = 'projects'
    id = Column(Integer, primary_key=True)
    nombre = Column(String)
    descripcion = Column(String)
    perfiles = Column(String)
    conocimientos_tecnicos = Column(String)
    habilidades_blandas = Column(String)

    def __init__(self, nombre, descripcion, perfiles, conocimientos_tecnicos,habilidades_blandas):
        Model.__init__(self)
        self.nombre = nombre
        self.descripcion = descripcion
        self.perfiles = perfiles
        self.conocimientos_tecnicos = json.dumps(conocimientos_tecnicos)
        self.habilidades_blandas = json.dumps(habilidades_blandas)

class ProjectSchema(Schema):
    id = fields.Int()
    nombre = fields.Str()
    descripcion = fields.Str()
    perfiles = fields.Str()
    conocimientos_tecnicos = fields.List(fields.Str())
    habilidades_blandas = fields.List(fields.Str())


class CreatedProjectJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


