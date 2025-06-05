class Classify : AppCompatActivity() {
    private lateinit var selectBtn: Button
    private lateinit var classifyBtn: Button
    private lateinit var imageView: ImageView
    private lateinit var bitmap: Bitmap
    private lateinit var labels: List<String>
    private val storageRef = FirebaseStorage.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_classify)
        labels = assets.open("labels.txt").bufferedReader().readLines()

        imageView = findViewById(R.id.imageView)
        selectBtn = findViewById(R.id.selectBtn)
        classifyBtn = findViewById(R.id.classifyBtn)

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(64, 64, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        selectBtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        classifyBtn.setOnClickListener {
            if (::bitmap.isInitialized) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Classifying...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                Handler().postDelayed({
                    var tensorImage = TensorImage(DataType.FLOAT32)
                    tensorImage.load(bitmap)
                    tensorImage = imageProcessor.process(tensorImage)

                    val model = OmbreCnn.newInstance(this)

                    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 64, 64, 3), DataType.FLOAT32)
                    inputFeature0.loadBuffer(tensorImage.buffer)

                    val outputs = model.process(inputFeature0)
                    val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

                    var maxIdx = 0
                    outputFeature0.forEachIndexed { index, fl ->
                        if (outputFeature0[maxIdx] < fl) {
                            maxIdx = index
                        }
                    }

                    model.close()
// Sample Codes for the Outfit Classification Model

// Add actual code here
