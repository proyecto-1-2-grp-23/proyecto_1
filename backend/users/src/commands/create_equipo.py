from .base_command import BaseCommannd
from ..models.user import User, UserSchema, CreatedUserJsonSchema
from ..models.equipo import Equipo, EquipoSchema, CreatedEquipoJsonSchema
from ..models.empresa import EmpresaSchema, Empresa
from ..session import Session
from flask import Flask, jsonify


class CreateEquipo(BaseCommannd):
    def __init__(self, data):
        self.data = data

    def execute(self):
        body: dict = self.data

        schema = EquipoSchema(only=("idEmpresa", "nombre", "descripcion")).load(body)
        obj = Equipo(**schema)
        session = Session()
        session.add(obj)
        session.commit()
        result = CreatedEquipoJsonSchema().dump(obj)
        session.close()
        return result
