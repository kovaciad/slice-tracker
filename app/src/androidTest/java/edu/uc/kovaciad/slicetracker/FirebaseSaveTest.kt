package edu.uc.kovaciad.slicetracker

//@RunWith(AndroidJUnit4::class)
//class FirebaseSaveTest {
//    private lateinit var viewModel: MainViewModel
//
//    @Before
//    fun getViewModel() {
//        viewModel = MainViewModel()
//    }
//
//
//    @Test
//    fun savePrinter() {
//        val printerTest = Printer("Ender 3 Pro", "bhhjf32", "jh34jk423")
//        val result = viewModel.save(printerTest, "printers")
//        Log.d("TEST", result.toString())
//        assert(result)
//        val printerResult = getPrinter(printerTest.id)
//        assert(printerResult)
//    }
//
//    private fun getPrinter(id: String): Boolean {
//        val docRef = viewModel.firestore.collection("printers").document(id)
//        var printerName = ""
//        docRef.get()
//            .addOnSuccessListener { document ->
//                run {
//                    if (document != null) {
//                        printerName = document.toObject(Printer::class.java).toString()
//                    }
//                }
//            }
//            .addOnFailureListener {
//                return@addOnFailureListener
//            }
//        return printerName == "Ender 3 Pro"
//    }
//}