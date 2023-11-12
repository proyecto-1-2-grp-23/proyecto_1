import array
import json
from ..models.project import Project
from .base_command import BaseCommannd
from ..session import Session
from ..errors.errors import IncompleteParams
from flask import jsonify
import requests
class ListProyectCandidato(BaseCommannd):

    def execute(self):

        url_candidatos = 'http://127.0.0.1:5000/users/candidatos'
        response_candidatos = requests.get(url_candidatos)

        if response_candidatos.status_code ==200:
            personalidades = json.loads(response_candidatos.text)
            return personalidades                  
        else:
            print(f'Error en la solicitud: {response_candidatos.status_code}')

    



    #def Listar_id_empresa(self, id_empresa):
    #    session = Session()
    #    equipos = session.query(Equipo).filter_by(id=id_empresa).all()
    #    resultado_equipos = [[equipo.id, equipo.nombre, equipo.descripcion, equipo.empleados] for equipo in equipos]
    #    equipos_dict = [{"id": equipo[0], "nombre": equipo[1], "descripcion": equipo[2], "empleados": equipo[3]} for equipo in resultado_equipos]
    #    return equipos_dict