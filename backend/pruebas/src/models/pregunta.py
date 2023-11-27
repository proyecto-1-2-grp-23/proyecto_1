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

    def __init__(self,idProyecto,dificultad,descripcion):
        Model.__init__(self)
        self.idProyecto = idProyecto
        self.dificultad = dificultad
        self.descripcion = descripcion

class PreguntaSchema(Schema):
    id = fields.Int()
    idProyecto = fields.Int()
    dificultad = fields.Int()
    descripcion = fields.Str()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedPreguntaJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()