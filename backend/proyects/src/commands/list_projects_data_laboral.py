import json
from .base_command import BaseCommannd
import requests
class ListProyectsDataLaboral(BaseCommannd):

    def execute(self):

        url_laboral = 'http://127.0.0.1:5000/users/dataLaboral'
        response_laboral = requests.get(url_laboral)        
        if response_laboral.status_code ==200:
            laborales = json.loads(response_laboral.text)
            return laborales
                  
        else:
            print(f'Error en la solicitud: {response_laboral.status_code}')

    



    #def Listar_id_empresa(self, id_empresa):
    #    session = Session()
    #    equipos = session.query(Equipo).filter_by(id=id_empresa).all()
    #    resultado_equipos = [[equipo.id, equipo.nombre, equipo.descripcion, equipo.empleados] for equipo in equipos]
    #    equipos_dict = [{"id": equipo[0], "nombre": equipo[1], "descripcion": equipo[2], "empleados": equipo[3]} for equipo in resultado_equipos]
    #    return equipos_dict