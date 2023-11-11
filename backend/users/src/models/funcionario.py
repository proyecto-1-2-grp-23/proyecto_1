from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Integer, ForeignKey
from .model import Model, Base


class DataFuncionario(Model, Base):
    __tablename__ = "funcionario_empresas"

    id = Column(Integer, primary_key=True)
    nombre_funcionario = Column(String)
    idEmpresa = Column(Integer, ForeignKey("users.id"))

    def __init__(
        self,
        nombre_funcionario,
        idEmpresa,
    ):
        Model.__init__(self)
        self.nombre_funcionario = nombre_funcionario
        self.idEmpresa = idEmpresa


class DataFuncionarioSchema(Schema):
    id = fields.Int()
    nombre_funcionario = fields.Str()
    idEmpresa = fields.Int()
    createdAt = fields.DateTime()


class CreatedDataFuncionarioJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class DataFuncionarioJsonSchema(Schema):
    id = fields.Int()
    nombre_funcionario = fields.Str()
    idEmpresa = fields.Int()
    createdAt = fields.DateTime()
