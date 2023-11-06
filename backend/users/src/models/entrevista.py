from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Boolean
from .model import Model, Base
from sqlalchemy import Column, String, DateTime, Boolean, Integer, ForeignKey


class Entrevista(Model, Base):
    __tablename__ = "entrevistas"
    id = Column(Integer, primary_key=True)
    idFuncionario = Column(Integer, ForeignKey('users.id'))
    idEmpresa = Column(Integer, ForeignKey("empresas.id"))
    idCandidato = Column(Integer, ForeignKey("candidatos.id"))
    fecha = Column(DateTime)
    lugar = Column(String)

    def __init__(self, idFuncionario, idEmpresa, idCandidato, fecha, lugar):
        Model.__init__(self)
        self.idFuncionario = idFuncionario
        self.idEmpresa = idEmpresa
        self.idCandidato = idCandidato
        self.fecha = fecha
        self.lugar = lugar


class EntrevistaSchema(Schema):
    id = fields.Int()
    idFuncionario = fields.Int()
    idEmpresa = fields.Int()
    idCandidato = fields.Int()
    fecha = fields.DateTime()
    lugar = fields.Str()
    createdAt = fields.DateTime()


class CreatedEntrevistaJsonSchema(Schema):
    id = fields.Int()
    idFuncionario = fields.Int()
    idEmpresa = fields.Int()
    idCandidato = fields.Int()
    fecha = fields.DateTime()
    lugar = fields.Str()
    createdAt = fields.DateTime()


class EntrevistaJsonSchema(Schema):
    id = fields.Int()
    idFuncionario = fields.Int()
    idEmpresa = fields.Int()
    idCandidato = fields.Int()
    fecha = fields.DateTime()
    lugar = fields.Str()
    createdAt = fields.DateTime()
