import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey, Date
from .model import Model, Base
from datetime import datetime, timedelta


class Respuesta(Model, Base):
    __tablename__ = "respuestas"

    id = Column(Integer, primary_key=True)
    idPregunta = Column(Integer)
    descripcion = Column(String)

    def __init__(self, idPregunta, descripcion):
        Model.__init__(self)
        self.idPregunta = idPregunta
        self.descripcion = descripcion


class RespuestaSchema(Schema):
    id = fields.Int()
    idPregunta = fields.Int()
    descripcion = fields.Str()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedRespuestaJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()
