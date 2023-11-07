from .base_command import BaseCommannd
from ..models.data_laboral import DataLaboral, DataLaboralSchema, CreatedDataLaboralJsonSchema
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
                "habilidades": self.data.pop("habilidades")
            }

            hora_inicio = self.data.pop("fecha_inicio")
            hora_fin = self.data.pop("fecha_fin")
            hora = '00:00:00'
            fecha_hora_inicio = hora_inicio + 'T' + hora
            fecha_hora_fin = hora_fin + 'T' + hora
            user_data_laboral["fecha_inicio"] = fecha_hora_inicio
            user_data_laboral["fecha_fin"] = fecha_hora_fin

            posted_data = DataLaboralSchema(
                only=(
                    "nombre_empresa",
                    "rol",
                    "funciones",
                    "fecha_inicio",
                    "fecha_fin",
                    "habilidades"
                )
            ).load(user_data_laboral)
            data_laboral = DataLaboral(**posted_data)
            session = Session()
           

            session.add(data_laboral)
            session.commit()
            new_user = CreatedDataLaboralJsonSchema().dump(data_laboral)
            session.close()
            return new_user

    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero
