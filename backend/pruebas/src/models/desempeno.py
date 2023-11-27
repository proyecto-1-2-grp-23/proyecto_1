import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey, Date, Boolean
from .model import Model, Base
from datetime import datetime, timedelta

class Desempeño(Model, Base):
    __tablename__ = "desempeños"

    id = Column(Integer, primary_key=True)
    idPrueba = Column(Integer)
    idCandidato = Column(Integer)
    idEmpresa = Column(Integer)

    def __init__(self, idPrueba, idCandidato, idEmpresa):
        Model.__init__(self)
        self.idPrueba = idPrueba
        self.idCandidato = idCandidato
        self.idEmpresa = idEmpresa


class DesempeñoSchema(Schema):
    id = fields.Int()
    idPrueba = fields.Int()
    idCandidato = fields.Int()
    idEmpresa = fields.Int()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedDesempeñoJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()