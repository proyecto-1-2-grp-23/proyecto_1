import requests

from ...src.session import Session
from ...src.models.proyect_candidato import ProyectCandidato, ProyectCandidatoJsonSchema
from ..commands.base_command import BaseCommannd


class ListCandidatosProyecto(BaseCommannd):
    def __init__(self, idProyecto):
        self.idProyecto = idProyecto

    def execute(self):
        session = Session()
        proyectos = (
            session.query(ProyectCandidato)
            .filter(ProyectCandidato.idProyecto == self.idProyecto)
            .all()
        )
        candidatosResult_json = []
        for registro in proyectos:
            candidatoPr_json = ProyectCandidatoJsonSchema().dump(registro)

            url_usuarios = "http://34.110.178.56/users/candidatos/" + str(
                registro.idCandidato
            )
            response = requests.get(url_usuarios)

            if response.status_code == 200:
                data = response.json()
            else:
                print(f"Error: {response.status_code}")
                print(response.text)

            candidatoPr_json = {**candidatoPr_json, "candidato": data}
            candidatosResult_json.append(candidatoPr_json)

        session.close()
        return candidatosResult_json
