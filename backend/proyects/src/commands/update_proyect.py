import json
from datetime import datetime
from flask import request

from .base_command import BaseCommannd
from ..models.project import Project, ProjectSchema, CreatedProjectJsonSchema
from ..session import Session
from flask import jsonify


class UpdateProject(BaseCommannd):
    def __init__(self, data, id):
        self.data = data
        self.id = id

    def execute(self):
        session = Session()
        try:
            project = session.query(Project).filter(Project.id == self.id).first()
            if project:
                startdate = datetime.fromisoformat(request.json["startDate"][:-1])
                finishdate = datetime.fromisoformat(request.json["finishDate"][:-1])

                project.conocimientos_tecnicos = request.json["conocimientos_tecnicos"]
                project.descripcion = request.json["descripcion"]
                project.finishDate = finishdate.date()
                project.habilidades_blandas = request.json["habilidades_blandas"]
                project.nombre = request.json["nombre"]
                project.perfiles = request.json["perfiles"]
                project.startDate = startdate.date()
                session.commit()
                session.close()
                return {"message": "Proyecto actualizado correctamente", "code": "ok"}
            else:
                return {"message": "Proyecto no encontrado"}

        except Exception as e:
            session.rollback()
            print(e)
            return {"message": f"Error al actualizar el proyecto: {str(e)}"}

        finally:
            session.close()
