import { createRouter, createWebHistory, createWebHashHistory} from 'vue-router'
import { defineAsyncComponent } from 'vue'

const router = createRouter({
    history: createWebHistory(),
    routes:[
        {
            path:'/',
            name:'Login',
            component: defineAsyncComponent(() => import('../views/Login.vue')),
            meta: { title: '欢迎登录' },
        },
        {
            path:'/register',
            name:'Register',
            component: defineAsyncComponent(() => import('../views/Register.vue')),
            meta: { title: '请注册' },
        },
        {
            path: '/*',
            redirect: '/',
        },
    ]
})

// 全局路由守卫
router.beforeEach((to, from, next)=>{
    // console.log(to, from)
    if (to.meta.title) {
      document.title = `${to.meta.title}`;
    }
    next()
})
  
router.afterEach((to, from)=>{
    // console.log(to, from)
    console.log('afterEach')
})

export default router
