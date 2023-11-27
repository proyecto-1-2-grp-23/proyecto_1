import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer
from .model import Model, Base
from datetime import datetime, timedelta

class ProyectCandidato(Model, Base):
    __tablename__ = 'proyectoCandidato'
    id = Column(Integer, primary_key=True)
    idProyecto = Column(Integer)
    idCandidato = Column(Integer)

    def __init__(self, idProyecto, idCandidato):
        Model.__init__(self)
        self.idProyecto = idProyecto
        self.idCandidato = idCandidato

class ProyectCandidatoSchema(Schema):
    id = fields.Int()
    idProyecto = fields.Integer()
    idCandidato = fields.Integer()


class CreatedProyectCandidatoJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()

class ProyectCandidatoJsonSchema(Schema):
    id = fields.Int()
    idProyecto = fields.Integer()
    idCandidato = fields.Integer()
    createdAt = fields.DateTime()