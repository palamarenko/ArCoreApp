package com.example.exchangeapp.ui.base

import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.CozyFragment
import ua.palamarenko.cozyandroid2.base_fragment.navigation.tasks.CozyViewModel

abstract class BaseFragment<T : CozyViewModel> : CozyFragment<T>()