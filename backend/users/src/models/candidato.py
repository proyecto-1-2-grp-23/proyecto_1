from marshmallow import Schema, fields
from sqlalchemy import Column, String, Integer, ForeignKey
from .model import Model, Base


class Candidato(Model, Base):
    __tablename__ = "candidatos"

    telefono = Column(String)
    nombreCompleto = Column(String)
    edad = Column(Integer)
    idiomas = Column(String)
    rasgosPersonalidad = Column(String)
    idEquipo = Column(Integer)
    idUsuario = Column(Integer, ForeignKey("users.id"))

    def __init__(
        self,
        telefono,
        nombreCompleto,
        edad,
        idiomas,
        rasgosPersonalidad,
        idUsuario=None,
    ):
        Model.__init__(self)
        self.telefono = telefono
        self.edad = edad
        self.nombreCompleto = nombreCompleto
        self.idiomas = idiomas
        self.rasgosPersonalidad = rasgosPersonalidad
        if id and idUsuario is not None:
            self.id = id
            self.idUsuario = idUsuario


class CandidatoSchema(Schema):
    id = fields.Int()
    telefono = fields.Str()
    nombreCompleto = fields.Str()
    edad = fields.Int()
    idiomas = fields.Str()
    rasgosPersonalidad = fields.Str()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()
    idEquipo = fields.Int()


class CreatedCandidatoJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class CandidatoJsonSchema(Schema):
    id = fields.Int()
    telefono = fields.Str()
    nombreCompleto = fields.Str()
    edad = fields.Int()
    idiomas = fields.Str()
    rasgosPersonalidad = fields.Str()
    idUsuario = fields.Int()
