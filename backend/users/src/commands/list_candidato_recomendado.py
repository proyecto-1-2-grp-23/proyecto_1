from .base_command import BaseCommannd
from ..models.candidato import Candidato, CandidatoJsonSchema
from ..models.data_laboral import DataLaboral, DataLaboralJsonSchema
from ..session import Session
import requests


class ListCandidatoRecomendado(BaseCommannd):
    def __init__(self, tecnica, personalidad):
        self.tecnica = tecnica
        self.personalidad = personalidad

    def execute(self):
        # print("self.personalidad",self.personalidad)
        session = Session()
        habilidades_proyects = "http://34.110.178.56/projects/listar-projects"
        response_habilidades_proyects = requests.get(habilidades_proyects)

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

        if (response_habilidades_proyects).status_code == 200:
            body_proyects = response_habilidades_proyects.json()
            candidatos = []

            for habilidades in body_proyects:
                palabras_habilidad_tecnica = habilidades[
                    "conocimientos_tecnicos"
                ].split(", ")
                palabras_habilidad_personalidad = habilidades[
                    "habilidades_blandas"
                ].split(", ")

                if (
                    self.tecnica in palabras_habilidad_tecnica
                    and self.personalidad in palabras_habilidad_personalidad
                ):
                    for tecnica_candidato in data_tecnica:
                        palabras_habilidad_tecnica_candidato = tecnica_candidato[
                            "habilidades"
                        ].split(",")
                    for laboral_candidato in data_personalidad:
                        palabras_habilidad_laboral_candidato = laboral_candidato[
                            "rasgosPersonalidad"
                        ].split(", ")
                        if (
                            self.personalidad in palabras_habilidad_laboral_candidato
                            and self.tecnica in palabras_habilidad_tecnica_candidato
                        ):
                            candidatos.append(laboral_candidato["nombreCompleto"])

                print(
                    f"Las palabras '{self.tecnica}' y '{self.personalidad}' se encuentran en 'habilidades'"
                )
            else:
                print(
                    f"Las palabras '{self.tecnica}' y '{self.personalidad}' no se encuentran en 'habilidades'"
                )

            return candidatos
