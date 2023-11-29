from .base_command import BaseCommannd
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..models.data_laboral import DataLaboral, DataLaboralJsonSchema
from ..session import Session
import json


class ListCandidatoRecomendado(BaseCommannd):
    def __init__(self, tecnica, personalidad):
        self.tecnica = tecnica
        self.personalidad = personalidad

    def execute(self):
        # print("self.personalidad",self.personalidad)
        session = Session()

        tecnica = (
            session.query(DataLaboral)
            .filter(DataLaboral.habilidades.like("%" + self.tecnica + "%"))
            .all()
        )
        data_tecnica = DataLaboralJsonSchema(many=True).dump(tecnica)

        personalidad = (
            session.query(Candidato)
            .filter(Candidato.rasgosPersonalidad.like("%" + self.personalidad + "%"))
            .all()
        )
        data_personalidad = CandidatoJsonSchema(many=True).dump(personalidad)
        session.close()

        print(data_tecnica)
        print("********************")
        print(data_personalidad)

        candidatos = []
        idUsuarios = set()

        for tecnicas in data_tecnica:
            valor = tecnicas.get("idUsuario")
            if valor is not None:
                idUsuarios.add(valor)

        for personalidad in data_personalidad:
            valor = personalidad.get("idUsuario")
            if valor is not None:
                idUsuarios.add(valor)

        # Obtener los valores de 'idUsuario' de cada JSON
        valores_personalidad = [d.get("idUsuario") for d in data_personalidad]
        valores_tecnica = [d.get("idUsuario") for d in data_tecnica]

        print("valores_personalidad")
        print(valores_personalidad)

        print("valores_tecnica")
        print(valores_tecnica)

        # Encontrar los objetos que contienen los valores de 'idUsuario' que coinciden en ambos JSON
        objetos_encontrados = [
            obj
            for obj in data_personalidad
            if obj["idUsuario"] in idUsuarios and obj["idUsuario"] in valores_tecnica
        ]

        # Verificar si se encontraron objetos
        if objetos_encontrados:
            print(
                f"Encontró los siguientes objetos en 'data_tecnica' que coinciden en 'idUsuario': {objetos_encontrados}"
            )
        else:
            print(f"No encontró coincidencias de 'idUsuario' en ambos JSON.")

        return objetos_encontrados
