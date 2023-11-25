import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey, Date, Boolean
from .model import Model, Base
from datetime import datetime, timedelta


class RespuestaEnviada(Model, Base):
    __tablename__ = "respuestas_enviadas"

    id = Column(Integer, primary_key=True)
    idRespuesta = Column(Integer)
    idEnvio = Column(Integer)
    correcta = Column(Boolean)

    def __init__(self, idRespuesta, idEnvio, correcta=None):
        Model.__init__(self)
        self.idRespuesta = idRespuesta
        self.idEnvio = idEnvio
        if correcta is not None:
            self.correcta = correcta


class RespuestaEnviadaSchema(Schema):
    id = fields.Int()
    idRespuesta = fields.Int()
    idEnvio = fields.Int()
    correcta = fields.Bool()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedRespuestaEnviadaJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()
