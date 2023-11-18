import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey, Date, Boolean
from .model import Model, Base
from datetime import datetime, timedelta


class Envio(Model, Base):
    __tablename__ = "respuestas_enviadas"

    id = Column(Integer, primary_key=True)
    idProyecto = Column(Integer)
    idCandidato = Column(Integer)
    observaciones = Column(String)

    def __init__(self, idProyecto, idCandidato, observaciones):
        Model.__init__(self)
        self.idProyecto = idProyecto
        self.idCandidato = idCandidato
        self.observaciones = observaciones


class EnvioSchema(Schema):
    id = fields.Int()
    idProyecto = fields.Int()
    idCandidato = fields.Int()
    observaciones = fields.Str()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedEnvioJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()
