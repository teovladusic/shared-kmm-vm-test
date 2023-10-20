package com.puzzle_agency.navigation.destination

sealed class DestinationScreen(protected val route: String) {

    abstract val fullRoute: String

    object Route {
        const val HOME = "home"
        const val AUTH1 = "auth1"
        const val AUTH2 = "auth2"
    }

    sealed class NoArgumentsDestination(route: String) : DestinationScreen(route) {
        operator fun invoke(): String = route

        override val fullRoute: String
            get() = route
    }

    data object HomeScreen : NoArgumentsDestination(Route.HOME)
    data object Auth1 : NoArgumentsDestination(Route.AUTH1)
    data object Auth2 : NoArgumentsDestination(Route.AUTH2)

    sealed class ArgsDestination(route: String, vararg params: String) : DestinationScreen(route) {

        protected abstract fun provideFullRoute(): String

        override val fullRoute: String
            get() = provideFullRoute()
    }

    data class UserDetailsScreen(val firstName: String, val lastName: String) :
        ArgsDestination("user_details", FIST_NAME_KEY, LAST_NAME_KEY) {

        companion object {
            private const val FIST_NAME_KEY = "firstName"
            private const val LAST_NAME_KEY = "lastName"
        }

        override fun provideFullRoute(): String = route.appendParams(
            FIST_NAME_KEY to firstName,
            LAST_NAME_KEY to lastName
        )
    }
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}