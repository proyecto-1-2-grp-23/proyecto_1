from .base_command import BaseCommannd
from ..models.data_laboral import (
    DataLaboral,
    DataLaboralSchema,
    CreatedDataLaboralJsonSchema,
)
from ..session import Session
from flask import Flask, jsonify


class CreateLaboral(BaseCommannd):
    try:

        def __init__(self, data):
            self.data = data

        def execute(self):
            user_data_laboral = {
                "nombre_empresa": self.data.pop("nombre_empresa"),
                "rol": self.data.pop("rol"),
                "funciones": self.data.pop("funciones"),
                "habilidades": self.data.pop("habilidades"),
                "idUsuario": self.data.pop("idUsuario"),
                "fecha_inicio": self.data.pop("fecha_inicio"),
                "fecha_fin": self.data.pop("fecha_fin"),
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
            data_laboral = DataLaboral(**posted_data)
            session = Session()
            session.add(data_laboral)
            session.commit()
            new_team = CreatedDataLaboralJsonSchema().dump(data_laboral)
            session.close()
            return new_team

    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero
