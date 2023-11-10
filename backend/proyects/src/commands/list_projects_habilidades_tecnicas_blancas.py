import array
import json
from ..models.project import Project
from .base_command import BaseCommannd
from ..session import Session
from ..errors.errors import IncompleteParams, UserAlreadyExists
from flask import jsonify
import requests
import urllib.parse
class ListTecnicasBlandas(BaseCommannd):
    def __init__(self, personalidad, habilidades):
        self.personalidad = personalidad
        self.habilidades = habilidades

    def execute(self):
        query_personalidad = self.personalidad
        query_habilidades = self.habilidades

        query_personalidad_2 = urllib.parse.unquote(query_personalidad)
        query_habilidades_2 = urllib.parse.unquote(query_habilidades)
        print(query_habilidades_2)
        print(query_personalidad_2)
        url_candidatos = 'http://127.0.0.1:5000/users/candidatos'
        url_laboral = 'http://127.0.0.1:5000/users/dataLaboral'
        response_candidatos = requests.get(url_candidatos)
        response_laboral = requests.get(url_laboral)
        # Verifica si la solicitud fue exitosa
        
        if response_candidatos.status_code == 200 and response_laboral.status_code ==200:
            personalidades = json.loads(response_candidatos.text)
            laborales = json.loads(response_laboral.text) 
            
            
            resultados = []
            for elementoLaboral in laborales:
                habilidades = elementoLaboral.get('habilidades', [])
                if(query_habilidades_2 in habilidades):
                    for elementoPersonalidad in personalidades:
                        personalidad = elementoPersonalidad.get('rasgosPersonalidad', [])
                        if(query_personalidad_2 in personalidad):
                            print(elementoPersonalidad['id'])
                            resultados.append((elementoPersonalidad))
            
            
            

                    return resultados
                       
            


            
        else:
            print(f'Error en la solicitud: {response_candidatos.status_code}')

    



    #def Listar_id_empresa(self, id_empresa):
    #    session = Session()
    #    equipos = session.query(Equipo).filter_by(id=id_empresa).all()
    #    resultado_equipos = [[equipo.id, equipo.nombre, equipo.descripcion, equipo.empleados] for equipo in equipos]
    #    equipos_dict = [{"id": equipo[0], "nombre": equipo[1], "descripcion": equipo[2], "empleados": equipo[3]} for equipo in resultado_equipos]
    #    return equipos_dict