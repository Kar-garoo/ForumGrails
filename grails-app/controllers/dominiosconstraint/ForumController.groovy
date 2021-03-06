package dominiosconstraint

class ForumController {
    def beforeInterceptor = {println "Se va a ejecutado la acción ${actionName}"}
    def afterInterceptor = {println "Se ha ejecutado la acción ${actionName}"}
    def show(){
        def forumShow = Forum.findById(params.id)
        [forumShow:forumShow]

    }
    def index(){
        render(view:'read')
    }
    def read(){
        ['forumInstance':Forum.list()]
    }

    def create(){

    }

    def createLogic(){

        def newForum = new Forum(params)

        if(!newForum.save(flush: true)){
            render(view:'create',model:[forum:newForum])
            return
        }else{
            render(view: 'read')
            print(eachError(bean: "${newForum}"))
        }
    }

    def update(){
        def forumUpdate = Forum.findById(params.id)
        [forumUpdate:forumUpdate]
    }

    def updateLogic(){
        def forumUpdate = Forum.findById(params.id)
        forumUpdate.name = params.name
        forumUpdate.category = params.category
        forumUpdate.admin = Admin.findById(params.admin.id)
        forumUpdate.post=Post.findById(params.post)
        if(!forumUpdate.save(flush: true)){
            render(view:'update',model:[forum:forumUpdate])
            return
        }else{
            render(view: 'read')
            print(eachError(bean: "${forumUpdate}"))
        }

    }

    def delete(){
        def deleteForum = Forum.findById(params.id)
        if(!deleteForum.delete(flush: true)){
            redirect(action: 'index')
        }else{
            redirect(action: 'index')
            print(eachError(bean: "${deleteForum}"))
        }
    }

    def buscar( ){

    }

    def buscarLogic( ){
        def forum = BuscarService.buscarForos(params)
        render(view: 'mostrarBusqueda', model: [forum:forum])


    }
}
