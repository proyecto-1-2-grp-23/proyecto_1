import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer
from .model import Model, Base
from datetime import datetime, timedelta


class EquipoCandidato(Model, Base):
    __tablename__ = "equipoCandidato"
    id = Column(Integer, primary_key=True)
    idEquipo = Column(Integer)
    idCandidato = Column(Integer)

    def __init__(self, idEquipo, idCandidato):
        Model.__init__(self)
        self.idEquipo = idEquipo
        self.idCandidato = idCandidato


class EquipoCandidatoSchema(Schema):
    id = fields.Int()
    idEquipo = fields.Integer()
    idCandidato = fields.Integer()


class CreatedEquipoCandidatoJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class EquipoCandidatoJsonSchema(Schema):
    id = fields.Int()
    idEquipo = fields.Integer()
    idCandidato = fields.Integer()
    createdAt = fields.DateTime()
