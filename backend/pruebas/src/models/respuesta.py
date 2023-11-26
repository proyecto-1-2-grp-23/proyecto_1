import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey, Date, Boolean
from .model import Model, Base
from datetime import datetime, timedelta


class Respuesta(Model, Base):
    __tablename__ = "respuestas"

    id = Column(Integer, primary_key=True)
    idPregunta = Column(Integer)
    descripcion = Column(String)
    esCorrecta = Column(Boolean)

    def __init__(self, idPregunta, descripcion, esCorrecta):
        Model.__init__(self)
        self.idPregunta = idPregunta
        self.descripcion = descripcion
        self.esCorrecta = esCorrecta


class RespuestaSchema(Schema):
    id = fields.Int()
    idPregunta = fields.Int()
    descripcion = fields.Str()
    esCorrecta = fields.Bool()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedRespuestaJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()
