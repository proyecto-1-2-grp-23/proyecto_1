from .base_command import BaseCommannd
from ..models.funcionario import (
    DataFuncionario,
    DataFuncionarioSchema,
    CreatedDataFuncionarioJsonSchema,
)
from ..session import Session
from flask import Flask, jsonify


class CreateFuncionario(BaseCommannd):
    try:

        def __init__(self, data):
            self.data = data

        def execute(self):
            user_data_funcionario = {
                "nombre_funcionario": self.data.pop("nombre_funcionario"),
                "idEmpresa": self.data.pop("idEmpresa"),
            }

            print(user_data_funcionario)

            posted_data = DataFuncionarioSchema(
                only=("nombre_funcionario", "idEmpresa")
            ).load(user_data_funcionario)
            data_laboral = DataFuncionario(**posted_data)
            session = Session()
            session.add(data_laboral)
            session.commit()
            new_team = CreatedDataFuncionarioJsonSchema().dump(data_laboral)
            session.close()
            return new_team

    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero
