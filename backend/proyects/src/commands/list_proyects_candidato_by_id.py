import json
import requests
from .base_command import BaseCommannd
from ..session import Session


class ListProyectsCandidatoById(BaseCommannd):
    def __init__(self, id):
        self.id = id

    def execute(self):
        url_laboral = f'http://127.0.0.1:5000/users/candidatos/{self.id}'
        response_laboral = requests.get(url_laboral)        
        if response_laboral.status_code ==200:
            laborales = json.loads(response_laboral.text)
            return laborales
        ##session = Session()
        ##
        ##
        ##
        ##candidato = (
        ##    session.query(Candidato).filter(Candidato.idUsuario == self.id).all()
        ##)
        ##datoCandidato = CandidatoJsonSchema(many=True).dump(candidato)
        ##session.close()
        return "Hola"