from .base_command import BaseCommannd
from ..models.equipo import Equipo, EquipoSchema
from ..models.empresa import EmpresaSchema, Empresa
from ..models.entrevista import (
    Entrevista,
    EntrevistaJsonSchema,
    EntrevistaSchema,
    CreatedEntrevistaJsonSchema,
)
from ..session import Session
from flask import Flask, jsonify


class CreateEntrevista(BaseCommannd):
    def __init__(self, data):
        self.data = data

    def execute(self):
        body: dict = self.data

        schema = EntrevistaSchema(
            only=("idFuncionario", "idEmpresa", "idCandidato", "fecha", "lugar")
        ).load(body)
        obj = Entrevista(**schema)
        session = Session()
        session.add(obj)
        session.commit()
        result = CreatedEntrevistaJsonSchema().dump(obj)
        session.close()
        return result
