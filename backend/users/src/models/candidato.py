import json
from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Boolean, Integer, ForeignKey
from .model import Model, Base
from sqlalchemy.orm import relationship

class Candidato(Model, Base):
    __tablename__ = "candidatos"
    
    id = Column(Integer, primary_key=True)
    telefono = Column(String)
    nombreCompleto = Column(String)
    edad = Column(Integer)
    idiomas = Column(String)
    rasgosPersonalidad = Column(String)
    idUsuario = Column(Integer, ForeignKey("users.id"))

    def __init__(
        self, telefono, nombreCompleto, edad, idiomas, rasgosPersonalidad, idUsuario
    ):
        Model.__init__(self)
        self.telefono = telefono
        self.edad = edad
        self.nombreCompleto = nombreCompleto
        self.idiomas = idiomas
        self.rasgosPersonalidad = rasgosPersonalidad
        self.idUsuario = idUsuario


class CandidatoSchema(Schema):
    id = fields.Int()
    telefono = fields.Str()
    nombreCompleto = fields.Str()
    edad = fields.Int()
    idiomas = fields.Str()
    rasgosPersonalidad = fields.Str()
    idUsuario = fields.Int()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedCandidatoJsonSchema(Schema):
    id = fields.Int()
    idUsuario = fields.Int()
    createdAt = fields.DateTime()


class CandidatoJsonSchema(Schema):
    id = fields.Int()
    telefono = fields.Str()
    nombreCompleto = fields.Str()
    edad = fields.Int()
    idiomas = fields.Str()
    rasgosPersonalidad = fields.Str()
    idUsuario = fields.Int()
