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
import random

resultados: list = ['APROBADO','RECHAZADO','EN ESPERA']

class CreateEntrevista(BaseCommannd):
    def __init__(self, data):
        self.data = data

    def execute(self):
        body: dict = self.data
        body['resultado'] = random.choice(resultados)
        schema = EntrevistaSchema(
            only=("idFuncionario", "idEmpresa", "idCandidato", "fecha", "lugar",'resultado')
        ).load(body)
        obj = Entrevista(**schema)
        session = Session()
        session.add(obj)
        session.commit()
        result = CreatedEntrevistaJsonSchema().dump(obj)
        session.close()
        return result
