from .base_command import BaseCommannd
from ..models.data_laboral import DataLaboral, DataLaboralSchema
from ..models.empresa import EmpresaSchema, Empresa
from ..session import Session
from flask import Flask, jsonify


class CreateEquipo(BaseCommannd):
    try:

        def __init__(self, data):
            self.data = data

        def execute(self):
            user_data_laboral = {
                "nombre_empresa": self.data.pop("nombre_empresa"),
                "rol": self.data.pop("rol"),
                "funciones": self.data.pop("funciones"),
                "fecha_inicio": self.data.pop("fecha_inicio"),
                "fecha_fin": self.data.pop("fecha_fin"),
                "habilidades": self.data.pop("habilidades"),
                "idUsuario": self.data.pop("idUsuario"),
            }

            posted_data = DataLaboralSchema(
                only=(
                    "nombre_empresa",
                    "rol",
                    "funciones",
                    "fecha_inicio",
                    "fecha_fin",
                    "habilidades",
                    "idUsuario",
                )
            ).load(user_data_laboral)

            session = Session()

            session.add(posted_data)
            session.commit()
            session.close()
            return posted_data

    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero
