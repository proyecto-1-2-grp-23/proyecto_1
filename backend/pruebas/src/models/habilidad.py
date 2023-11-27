import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey, Date, Boolean
from .model import Model, Base
from datetime import datetime, timedelta

class Habilidad(Model, Base):
    
    __tablename__ = "habilidades"

    id = Column(Integer, primary_key=True)
    idPrueba = Column(Integer)
    habilidad = Column(String)
    puntaje = Column(Integer)

    def __init__(self, idPrueba, habilidad, puntaje):
        Model.__init__(self)
        self.idPrueba = idPrueba
        self.habilidad = habilidad
        self.puntaje = puntaje
    
class HabilidadSchema(Schema):
    id = fields.Int()
    idPrueba = fields.Int()
    habilidad = fields.Str()
    puntaje = fields.Int()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedHabilidadJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()