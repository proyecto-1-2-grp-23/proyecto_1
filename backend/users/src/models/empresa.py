from marshmallow import Schema, fields
from sqlalchemy import Column, String, DateTime, Boolean, Integer, ForeignKey
from .model import Model, Base


class Empresa(Model, Base):
    __tablename__ = "empresas"

    razonSocial = Column(String)
    tipoEmpresa = Column(String)
    verticalesNegocio = Column(String)
    idUsuario = Column(Integer, ForeignKey("users.id"))

    def __init__(
        self, razonSocial, tipoEmpresa, verticalesNegocio, id=None, idUsuario=None
    ):
        Model.__init__(self)
        self.razonSocial = razonSocial
        self.tipoEmpresa = tipoEmpresa
        self.verticalesNegocio = verticalesNegocio
        self.idUsuario = idUsuario


class EmpresaSchema(Schema):
    id = fields.Int()
    razonSocial = fields.Str()
    tipoEmpresa = fields.Str()
    verticalesNegocio = fields.Str()
    idUsuario = fields.Int()
    expireAt = fields.DateTime()
    createdAt = fields.DateTime()


class CreatedEmpresaJsonSchema(Schema):
    id = fields.Int()
    createdAt = fields.DateTime()


class EmpresaJsonSchema(Schema):
    id = fields.Int()
    razonSocial = fields.Str()
    tipoEmpresa = fields.Str()
    verticalesNegocio = fields.Str()
    idUsuario = fields.Int()
